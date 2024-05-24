import { ReactNode } from "react";

export type DashboardSidebarItemProps = {
  item: string;
  url: string;
  icon: ReactNode;
  badge?: number;
};
