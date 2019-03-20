const DEFAULT_PAGE_NO = 1;
const DEFAULT_PAGE_SIZE = 10;

class PaginationHelper {

  static defaultPaginationConfig = {
    defaultCurrent: DEFAULT_PAGE_NO,
    defaultPageSize: DEFAULT_PAGE_SIZE,
    current: DEFAULT_PAGE_NO,
    total: 0,
    pageSize: DEFAULT_PAGE_SIZE,
    showSizeChanger: true,
    showQuickJumper: true,
    showTotal: total => `共 ${total} 条`
  };

  static formatPagination(data) {
    return {
      defaultCurrent: DEFAULT_PAGE_NO,
      defaultPageSize: DEFAULT_PAGE_SIZE,
      current: data.current,
      total: data.total,
      pageSize: data.size,
      showSizeChanger: true,
      showQuickJumper: true,
      showTotal: total => `共 ${total} 条`,
    };
  };

  /**
   * data.total 数据总数
   * payload.pageNo 页码
   * payload.pageSize 每页总数
   */
  static formatPagination(data, payload) {
    return {
      defaultCurrent: DEFAULT_PAGE_NO,
      defaultPageSize: DEFAULT_PAGE_SIZE,
      current: payload.pageNo,
      pageSize: payload.pageSize,
      total: data.total,
      showSizeChanger: true,
      showQuickJumper: true,
      showTotal: total => `共 ${total} 条`,
    };
  };

  //获取初始页码
  static defaultPayload = {
    pageNo: DEFAULT_PAGE_NO,
    pageSize: DEFAULT_PAGE_SIZE
  }

}

export default PaginationHelper;
export {PaginationHelper};
