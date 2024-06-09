export type Status = "ACT" | "INA";

export interface PageInfo {
  page: number;
  rows: number;
}

export interface Page<T> extends AppPagination {
  start_position: number;
  total_records: number;
  records_count: number;
  page_data: T[];
}


export type AppPagination = {
  current_page: number;
  page_length: number;
  total_pages: number;
};

export interface DispatchAction {
  type: string;
  payload: any;
}


export class RouteConfig {
  route: string;
  show_title: boolean;

  constructor(data?: any) {
    this.route = data.route || '';
    this.show_title = data.show_title || false;
  }
}

export enum MasterDataAction {
  GET_ALL_EXECUTION_PERIODS = "GET_ALL_EXECUTION_PERIODS",
}

export interface HeaderParams {
  isFileDownload?: boolean;
  showLoading?: boolean;
  skipAuth?: boolean;
  message?: string;
  showToast?: boolean;
  isFileUpload?: boolean;
  isMultipart?: boolean;
}


export type UserType = "ADMIN" | "COMPANY_USER";

export type PrivilegeModule = "ADMIN" | "CLIENT" | "COMMON";

export type Privilege = {
  privilegeID: number;
  privilegeCode: string;
  privilegeName: string;
  description: string;
  privilegeCategory: string;
  status: Status;
};

export type PrivilegeMap = {
  [key in string]: Privilege[];
};