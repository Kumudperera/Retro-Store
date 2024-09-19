import Constants from "@/utils/constants";
import { PageProps } from "../../../.next/types/app/layout";


export default function Dashboard(props: PageProps) {
  props.params.configs = Constants.PAGES.DASHBOARD;
  return <div></div>;
}
