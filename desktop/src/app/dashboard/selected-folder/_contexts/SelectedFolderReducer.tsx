import { SelectedFolderAction, SelectedFoldersData } from "../@types";
import { DispatchAction, MasterDataAction } from "@/common/@types";
import Constants from "@/utils/constants";

const SelectedFolderReducer = (
  state: SelectedFoldersData,
  action: DispatchAction,
) => {
  switch (action.type) {
    case SelectedFolderAction.SET_VIEW_FULL_SEARCH_VIEW:
      return {
        ...state,
        viewFullSearchView: action.payload,
      };

    case SelectedFolderAction.GET_COUNT: {
      return {
        ...state,
        announcementCount: action.payload,
      };
    }

    case SelectedFolderAction.GET_PAGED_DATA: {
      return {
        ...state,
        pagedAnnouncement: { ...action.payload },
      };
    }

    case SelectedFolderAction.SET_PAGE_INFO: {
      return {
        ...state,
        pageInfo: action.payload,
      };
    }
    case SelectedFolderAction.SET_SEARCH_DATA: {
      return {
        ...state,
        searchData: { ...action.payload },
      };
    }
    case MasterDataAction.GET_ALL_EXECUTION_PERIODS: {
      return {
        ...state,
        searchData: { ...action.payload },
      };
    }

    case SelectedFolderAction.ON_RESET_SEARCH: {
      return {
        ...state,
        pageInfo: {
          page: 1,
          rows: Constants.GRID_RESULT_ROW_DEFAULT_SIZE,
        },
        searchData: {},
      };
    }

    case SelectedFolderAction.ON_GET_ANNOUNCEMENT_BY_ID:
    case SelectedFolderAction.ON_USER_ACKNOWLEDGE:
      return {
        ...state,
        announcementForm: action.payload,
      };

    default:
      return state;
  }
};

export default SelectedFolderReducer;
