import React, { PureComponent, Fragment, Component } from 'react';
import { Form, Card, Table, Button, Divider, Modal, Input } from 'antd';
import moment from 'moment';
import { connect } from 'dva';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import PaginationHelper from '../../../helpers/PaginationHelper';
import styles from './ProductAttrList.less';

const FormItem = Form.Item;
const CreateForm = Form.create()(props => {
  const { modalVisible, form, handleAdd, handleModalVisible, modalType, initValues } = props;

  const okHandle = () => {
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      let pid = fieldsValue.pid;
      if (fieldsValue.pid) {
        pid = pid.split('-')[1];
        fieldsValue.pid = pid;
      }
      form.resetFields();
      handleAdd({
        fields: fieldsValue,
        modalType,
        initValues,
      });
    });
  };

  const selectStyle = {
    width: 200,
  };

  function onTypeChange(event) {
    initValues.type = parseInt(event.target.value);
  }

  const title = modalType === 'add' ? '添加规格' : '编辑规格';
  const okText = modalType === 'add' ? '添加' : '编辑';
  return (
    <Modal
      destroyOnClose
      title={title}
      visible={modalVisible}
      onOk={okHandle}
      okText={okText}
      onCancel={() => handleModalVisible()}
    >
      <FormItem labelCol={{ span: 5 }} wrapperCol={{ span: 15 }} label="规格名称">
        {form.getFieldDecorator('name', {
          initialValue: initValues ? initValues.name : null,
          rules: [{ required: true, message: '请输入规格名称！', min: 2 }],
        })(<Input placeholder="规格名称" />)}
      </FormItem>
    </Modal>
  );
});

@connect(({ productAttrList, loading }) => ({
  productAttrList,
  attrData: productAttrList.attrData,
  loading: loading.models.productAttrList,
}))
@Form.create()
export default class ProductAttrList extends PureComponent {
  state = {
    modalVisible: false,
    modalType: 'add', //add or update
    initValues: {},
  };

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'productAttrList/page',
      payload: {
        ...PaginationHelper.defaultPayload,
      },
    });
  }

  handleModalVisible = (flag, modalType, initValues) => {
    this.setState({
      modalVisible: !!flag,
      initValues: initValues || {},
      modalType: modalType || 'add',
    });
  };

  render() {
    const { attrData, productAttrList, loading, pagination } = this.props;
    const columns = [
      {
        title: '规格名称',
        dataIndex: 'name',
      },
      {
        title: '状态',
        dataIndex: 'status',
        render: val => <span>{val === 1 ? '开启' : '禁用'}</span>,
      },
      {
        title: '创建时间',
        dataIndex: 'createTime',
        sorter: true,
        render: val => <span>{moment(val).format('YYYY-MM-DD')}</span>,
      },
      {
        title: '操作',
        render: (text, record) => (
          <Fragment>
            <a onClick={() => this.handleModalVisible(true, 'update', record)}>编辑</a>
            <Divider type="vertical" />
            <a className={styles.tableDelete} onClick={() => this.handleDelete(record)}>
              删除
            </a>
          </Fragment>
        ),
      },
    ];

    const { modalVisible, modalType, initValues } = this.state;

    const parentMethods = {
      handleAdd: this.handleAdd,
      handleModalVisible: this.handleModalVisible,
      modalType,
      initValues,
    };

    return (
      <PageHeaderWrapper>
        <Card>
          <div className={styles.tableList}>
            <div className={styles.tableListOperator}>
              <Button
                icon="plus"
                type="primary"
                onClick={() => this.handleModalVisible(true, 'add', {})}
              >
                新建规格
              </Button>
            </div>
          </div>
          <Table
            defaultExpandAllRows={true}
            columns={columns}
            dataSource={attrData.attrs ? attrData.attrs : []}
            rowKey="id"
            loading={loading}
            pagination={pagination}
          />
        </Card>
        {modalVisible ? <CreateForm {...parentMethods} modalVisible={modalVisible} /> : null}
      </PageHeaderWrapper>
    );
  }
}
