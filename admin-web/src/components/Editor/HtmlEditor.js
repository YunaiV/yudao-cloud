import React from "react";

import 'braft-editor/dist/index.css'
import BraftEditor from 'braft-editor'
import { ContentUtils } from 'braft-utils'
import { ImageUtils } from 'braft-finder'

import {fileGetQiniuToken} from "../../services/admin";
import uuid from "js-uuid";
import * as qiniu from "qiniu-js";
import {Icon, Upload} from "antd";

class HtmlEditor extends React.Component {

  state = {
    editorState: BraftEditor.createEditorState(null),
  };

  handleChange = (editorState) => {
    this.setState({editorState})
  };

  uploadHandler = async (param) => {
    if (!param.file) {
      return false
    }
    debugger;
    const tokenResult = await fileGetQiniuToken();
    if (tokenResult.code !== 0) {
      alert('获得七牛上传 Token 失败');
      return false;
    }
    let token = tokenResult.data;
    let that = this;
    const reader = new FileReader();
    const file = param.file;
    reader.readAsArrayBuffer(file);
    let fileData = null;
    reader.onload = (e) => {
      let key = uuid.v4(); // TODO 芋艿，可能后面要优化。MD5？
      let observable = qiniu.upload(file, key, token); // TODO 芋艿，最后后面去掉 qiniu 的库依赖，直接 http 请求，这样更轻量
      observable.subscribe(function () {
        // next
      }, function (e) {
        // error
        // TODO 芋艿，后续补充
        // debugger;
      }, function (response) {
        // complete
        that.setState({
          editorState: ContentUtils.insertMedias(that.state.editorState, [{
            type: 'IMAGE',
            url: 'http://static.shop.iocoder.cn/' + response.key,
          }])
        })
      });
    }
  };

  getHtml() {
    return this.state.editorState.toHTML();
  }

  setHtml = (html) => {
    this.setState({
      editorState: BraftEditor.createEditorState(html),
    })
  };

  isEmpty = () => {
    return this.state.editorState.isEmpty();
  };

  render() {
    // const controls = ['bold', 'italic', 'underline', 'text-color', 'separator', 'link', 'separator'];
    const extendControls = [
      {
        key: 'antd-uploader',
        type: 'component',
        component: (
          <Upload
            accept="image/*"
            showUploadList={false}
            customRequest={this.uploadHandler}
          >
            {/* 这里的按钮最好加上type="button"，以避免在表单容器中触发表单提交，用Antd的Button组件则无需如此 */}
            <button type="button" className="control-item button upload-button" data-title="插入图片">
              <Icon type="picture" theme="filled" />
            </button>
          </Upload>
        )
      }
    ];

    return (
      <div style={{border: '1px solid #d1d1d1', 'border-radius': '5px'}}>
        <BraftEditor
          value={this.state.editorState}
          onChange={this.handleChange}
          defaultValue={this.state.initialContent}
          // controls={controls}
          extendControls={extendControls}
          contentStyle={{height: 200}}
        />
      </div>
    )
  }

}


{/**/}

// </div>

export default HtmlEditor;
