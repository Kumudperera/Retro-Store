
import * as _ from "lodash";
import filter from "lodash/filter";
import indexOf from "lodash/indexOf";
import keys from "lodash/keys";
import { PrivilegeMap, PrivilegeModule } from '@/common/@types';
import constants from "@/utils/constants";

class EventEmitter<T = any> {
    private events: Record<string, Set<(...args: T[]) => void>> = {};
  
    private getEventListByName(eventName: string): Set<(...args: T[]) => void> {
      if (!this.events[eventName]) {
        this.events[eventName] = new Set();
      }
      return this.events[eventName];
    }
  
    on(eventName: string, fn: (...args: T[]) => void): void {
      this.getEventListByName(eventName).add(fn);
    }
  
    once(eventName: string, fn: (...args: T[]) => void): void {
      const self = this;
  
      const onceFn = (...args: T[]): void => {
        self.removeListener(eventName, onceFn);
        fn.apply(self, args);
      };
      this.on(eventName, onceFn);
    }
  
    emit(eventName: string, ...args: T[]): void {
      this.getEventListByName(eventName).forEach((fn: (...args: T[]) => void) => {
        fn.apply(this, args);
      });
    }
  
    removeListener(eventName: string, fn: (...args: T[]) => void): void {
      this.getEventListByName(eventName).delete(fn);
    }
  }

class RetroStoreUtils {
    static EventEmitter = EventEmitter;
  
    static hasPrivilege(privilege: string, allPrivileges: string[]) {
      return indexOf(allPrivileges, privilege.trim()) !== -1;
    }
  
    static hasAnyPrivilege(privileges: string[], allPrivileges: string[]) {
      let hasPrivilege = false;
  
      for (const privilege of privileges) {
        if (this.hasPrivilege(privilege, allPrivileges)) {
          hasPrivilege = true;
          break;
        }
      }
  
      return hasPrivilege;
    }
  
    static filterPrivilegeByModule(
      modules: PrivilegeModule[],
      categoryWisePrivileges: any,
    ) {
      const filtered: PrivilegeMap = {};
  
      keys(categoryWisePrivileges).forEach((category) => {
        modules.forEach((reqModule) => {
          const filteredPrivileges = filter(
            categoryWisePrivileges[category],
            (privilege: any) => privilege.privilegeModule == reqModule,
          );
          if (filteredPrivileges.length > 0) {
            if (!filtered[category]) {
              filtered[category] = [];
            }
            filtered[category] = [...filtered[category], ...filteredPrivileges];
          }
        });
      });
      return filtered;
    }
  
    static getImageUrl(folderName: string, imageName: any) {
      let imageUrl = constants.APP_PROPERTIES.storageBaseUrl + folderName;
      if (imageName === "ae9174dcc1dc16715b894e7f35ea970d.png") {
        imageUrl = constants.APP_PROPERTIES.storageBaseUrl + "/";
      }
  
      if (imageName) {
        imageUrl += imageName;
      } else {
        imageUrl += "default.jpg";
      }
  
      return imageUrl;
    }
  }
  
  export default RetroStoreUtils;