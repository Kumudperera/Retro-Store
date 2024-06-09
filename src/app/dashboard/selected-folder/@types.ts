import { Page, PageInfo, Status } from "@/common/@types";
import { ProcessExecutionPeriod } from "@/common/masterData/@types";

export enum SelectedFolderAction {
  GET_ALL_EXECUTION_PERIODS = "GET_ALL_EXECUTION_PERIODS",
  GET_SELECTED_FOLDER = "GET_SELECTED_FOLDER",
}

export type SelectedFoldersData = {
  page_info: PageInfo;
  paged_selected_folders: Page<SelectedFolderDTO> | undefined
  /* searchData: SelectedFolderSearchData | undefined;
  pagedAnnouncement: Page<SelectedFolderDTO> | undefined;
  announcementForm: AnnouncementFormDTO; */
};

export type SelectedFolderAddEditData = {
  selected_folder: SelectedFolderDTO | undefined,
  all_periods: ProcessExecutionPeriod[],
};

class SelectedFolderDTO {
  id: number;
  folder_path: string;
  process_execution_period_id: number;
  process_execution_period_name: string
  user_machine_id: number;
  status: Status;

  constructor(data? : any) {
    this.id = data?.id ?? null;
    this.folder_path = data?.folder_path ?? "";
    this.process_execution_period_id = data?.process_execution_period_id ?? null;
    this.process_execution_period_name = data?.process_execution_period_name ?? "";
    this.user_machine_id = data?.user_machine_id ?? null;
    this.status = data?.status ?? "ACT";
  }
}
/* export type SelectedFolderDTO = {
  id: number;
  folder_path: string;
  process_execution_period_id: number;
  period_name: string,
  user_machine_id: number;
  status: Status;
}; */

export type AnnouncementFormDTO = {
  announcementAcknowledgementID: string;
  announcementID: string;
  userID: string;
  title: string;
  description: string;
  userAckRemark: string;
  acknowledgementNeeded: string;
  enableCommenting: string;
  announcementType: string;
  ackStatus: string;
  status: string;
};

export type SelectedFolderSearchData = {
  title: string;
  description: string;
};
