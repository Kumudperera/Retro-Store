import { DispatchAction, MasterDataAction } from "@/common/@types";
import { SelectedFolderAction, SelectedFolderAddEditData } from "../../@types";

const SelectedFolderReducer = (
  state: SelectedFolderAddEditData,
  action: DispatchAction,
) => {
  switch (action.type) {
    case MasterDataAction.GET_ALL_EXECUTION_PERIODS:
      console.log("action. Payload: ", action.payload);
      return {
        ...state,
        all_periods: action.payload ?? [],
      };

    case SelectedFolderAction.GET_SELECTED_FOLDER:
      return {
        ...state,
        selected_folder: action.payload,
      };

    default:
      return state;
  }
};

export default SelectedFolderReducer;
