import { Page, PageInfo, Status } from "@/lib/@types";

export enum SelectedFolderAction {
  GET_COUNT = "GET_COUNT",
  SET_PAGE_INFO = "SET_PAGE_INFO",
  GET_PAGED_DATA = "GET_PAGED_DATA",
  ON_RESET_SEARCH = "ON_RESET_SEARCH",
  SET_SEARCH_DATA = "SET_SEARCH_DATA",
  SET_VIEW_FULL_SEARCH_VIEW = "SET_VIEW_FULL_SEARCH_VIEW",
  ON_GET_ANNOUNCEMENT_BY_ID = "ON_GET_ANNOUNCEMENT_BY_ID",
  ON_USER_ACKNOWLEDGE = "ON_USER_ACKNOWLEDGE",
}

export type SelectedFoldersData = {
  page_info: PageInfo;
  pagedSelectedFolders: Page<SelectedFolderDTO> | undefined
  /* searchData: SelectedFolderSearchData | undefined;
  pagedAnnouncement: Page<SelectedFolderDTO> | undefined;
  announcementForm: AnnouncementFormDTO; */
};

export type SelectedFolderDTO = {
  id: number;
  folder_path: string;
  process_execution_period_id: number;
  period_name: string,
  user_machine_id: number;
  status: Status;
};

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
