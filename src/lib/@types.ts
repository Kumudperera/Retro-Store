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

