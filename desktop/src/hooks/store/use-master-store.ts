import { getApplicationProperties } from "@/services/auth/LoginService";
import Constants from "@/utils/constants";
import { create } from "zustand";

interface UseMasterStoreInterface {
  appProperties?: Object;
  fetchAppProperties: () => void;
}

export const useMasterStore = create<UseMasterStoreInterface>((set) => ({
  appProperties: undefined,
  fetchAppProperties: async () => {
    const response = await getApplicationProperties();
    if (response.data) {
      set({ appProperties: response.data.result });
      Constants.APP_PROPERTIES = { ...response.data.result };
    } else {
      set({ appProperties: {} });
    }
    return response;
  },
}));
