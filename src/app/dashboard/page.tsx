import { PageProps } from "../../../.next/types/app/layout";
import { navigationConfigs } from "./navigationConfigs";

export default function Dashboard(props: PageProps) {
  props.params.configs = navigationConfigs.dashboard;
  return <div>Dashboard</div>;
}
