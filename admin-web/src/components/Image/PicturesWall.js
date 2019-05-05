import React from "react";
import {fileGetQiniuToken} from "../../services/admin";
import uuid from "js-uuid";
import * as qiniu from "qiniu-js";
import {Icon, Modal, Upload} from "antd";

import styles from './PicturesWall.less';

/**
 * 照片墙，带有上传图片到七牛。
 */
class PicturesWall extends React.Component {
  state = {
    token: undefined, // 七牛 token


    previewVisible: false,
    previewImage: '',

    fileList: [
      //   { // 目前图片
      //   uid: -1,
      //   name: 'xxx.png',
      //   status: 'done',
      //   url: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
      // }
    ],
  };

  componentDidMount() {
    if (this.props.urls) {
      this.setUrls(this.props.urls);
    }
  }

  handleCancel = () => this.setState({ previewVisible: false })

  handlePreview = (file) => {
    this.setState({
      previewImage: file.url || file.thumbUrl,
      previewVisible: true,
    });
  }

  beforeUpload = async () => {
    const tokenResult = await fileGetQiniuToken();
    if (tokenResult.code !== 0) {
      alert('获得七牛上传 Token 失败');
      return false;
    }
    this.setState({
      token: tokenResult.data,
    });
    return true;
  };

  customRequest = ({action,
                     file,
                     headers,
                     onError,
                     onProgress,
                     onSuccess,
                     withCredentials,}) => {
    // fs.readFile(path, function(err, data) {
    //   if (err) return;
    //   let md5Value= crypto.createHash('md5').update(data, 'utf8').digest('hex');
    //   let observable = qiniu.upload(file, md5Value, this.state.token);
    //     observable.subscribe(function () {
    //       // next
    //     }, function () {
    //       // error
    //     }, function (response) {
    //       // complete
    //       debugger;
    //       onSuccess(response, file);
    //     });
    // });

    // 使用 FileReader 将上传的文件转换成二进制流，满足 'application/octet-stream' 格式的要求
    const reader = new FileReader();
    reader.readAsArrayBuffer(file);
    let fileData = null;
    reader.onload = (e) => {
      // 在文件读取结束后执行的操作
      fileData = e.target.result;
      // debugger;
      // let md5Value= crypto.createHash('md5').update(fileData, 'utf8').digest('hex');
      // 上传文件
      // fileUploadQiniu(fileData);
      // debugger;
      // 使用 axios 进行文件上传的请求
      // axios.put(action, fileData, {
      //   withCredentials,
      //   headers,
      //   onUploadProgress: ({ total, loaded }) => {
      //     // 进行上传进度输出，更加直观
      //     onProgress({ percent: Math.round(loaded / total * 100).toFixed(2) }, file);
      //   },
      // }).then(response => {
      //   onSuccess(response, file);
      // })
      //   .catch(onError);
      let key = uuid.v4(); // TODO 芋艿，可能后面要优化。MD5？
      let observable = qiniu.upload(file, key, this.state.token); // TODO 芋艿，最后后面去掉 qiniu 的库依赖，直接 http 请求，这样更轻量
      observable.subscribe(function () {
        // next
      }, function () {
        // error
        // TODO 芋艿，后续补充
        debugger;
      }, function (response) {
        // complete
        // debugger;
        response.url = 'http://static.shop.iocoder.cn/' + response.key; // 需要设置，用于后续 onSuccess ，合并到 file 中，从而设置到 fileList
        onSuccess(response, file);
      });
    };
    return {
      abort() {
        console.log('upload progress is aborted.');
      },
    };
  };

  handleChange = ({ file, fileList }) => {
    if (file.response && file.response.url) {
      // debugger
      // file.url =
      for (let i in fileList) {
        if (fileList[i].uid === file.uid) {
          fileList[i].url = file.response.url;
        }
      }
    }
    this.setState({ fileList });
  }

  getUrls = () => {
    let urls = [];
    for (let i in this.state.fileList) {
      urls.push(this.state.fileList[i].url);
    }
    return urls;
  };

  getUrl = () => {
    let urls = this.getUrls();
    if (urls && urls.length > 0) {
      return urls[0];
    }
    return undefined;
  };

  setUrls = (urls) => {
    // let urls = this.props.urls;
    if (urls) {
      let fileList = [];
      for (let i in urls) {
        let url = urls[i];
        fileList.push({
          uid: -i,
          name: url,
          status: 'done',
          url,
        });
      }
      this.setState({
        fileList: fileList,
      })
    }
  };

  render() {
    const { previewVisible, previewImage, fileList } = this.state;
    const uploadButton = (
      <div>
        <Icon type="plus" />
        <div className="ant-upload-text">Upload</div>
      </div>
    );
    return (
      <div className="clearfix">
        <Upload
          // action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
          // action="https://up-z2.qiniu.com"
          listType="picture-card"
          fileList={fileList}
          onPreview={this.handlePreview}
          onChange={this.handleChange}
          beforeUpload={this.beforeUpload}
          customRequest={this.customRequest}
        >
          {fileList.length >= this.props.maxLength ? null : uploadButton}
        </Upload>
        <Modal visible={previewVisible} footer={null} onCancel={this.handleCancel}>
          <img alt="example" style={{ width: '100%' }} src={previewImage} />
        </Modal>
      </div>
    );
  }
};

PicturesWall.propTypes = {
  maxLength: Number, // 最大照片墙图片数量
  urls: Array,  // 初始图片列表
};

export default PicturesWall;
