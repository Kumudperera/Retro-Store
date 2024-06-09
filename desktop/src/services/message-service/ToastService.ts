import RetroStoreUtils from "@/utils/retroStoreUtils";

class ToastService extends RetroStoreUtils.EventEmitter {
  showDefaultMessage = (message: string) => {
    this.emit("DEFAULT", message);
  };

  showSuccessMessage = (message: string) => {
    this.emit("SUCCESS", message);
  };

  showWarnMessage = (message: string) => {
    this.emit("WARN", message);
  };

  showErrorMessage = (message: string) => {
    this.emit("ERROR", message);
  };
}

const toastService = new ToastService();

export default toastService;
