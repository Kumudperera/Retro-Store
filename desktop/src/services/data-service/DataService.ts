import { invoke } from "@tauri-apps/api/tauri";
import toastService from "../message-service/ToastService";

class DataService {
  async request(data: any) {
    try {
      const response = await invoke(data.methodName);
      return response;
    } catch (error: any) {
      console.log("Error: ", error);
      toastService.showErrorMessage(error);
    }
  }
}

const instance = new DataService();

export default instance;
