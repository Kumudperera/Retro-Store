import { MasterDataAction } from "@/common/@types";
import dataService from '@/services/data-service/DataService';
import { MASTER_INVOKES_GET_ALL_EXECUTION_PERIODS } from "./masterInvokes";

export function getAllExecutionPeriods() {
  let invokeCall = MASTER_INVOKES_GET_ALL_EXECUTION_PERIODS;
  const request = dataService.request(invokeCall);
  return (dispatch: React.Dispatch<any>) => {
    request.then((response: any) => {
      dispatch({
        type: MasterDataAction.GET_ALL_EXECUTION_PERIODS,
        payload: response,
      });
    });
  };
}
