// export enum ExecutionPeriod {
//   DAILY = "DAILY",
//   WEEKLY = "WEEKLY",
//   MONTHLY = "MONTHLY",
// }

// export const executionPeriods = [
//   { name: "Daily", value: ExecutionPeriod.DAILY },
//   /* { name: "Weekly", value: ExecutionPeriod.WEEKLY },
//   { name: "Monthly", value: ExecutionPeriod.MONTHLY }, */
// ];

export class ExecutionPeriod {
  public id: number;
  public name: string;
  public description: string;

  constructor(data?: any) {
    this.id = data.id || null;
    this.name = data.name || "";
    this.description = data.description || "";
  }
}

export class SelectedFolderDTO {
  public selected_folder: string;
  public execution_period_id: string;
  public execution_time: any;

  constructor(data?: any) {
    this.selected_folder = data?.selected_folder ?? "";
    this.execution_period_id = data?.executed_id ?? "1";
    this.execution_time = data?.execution_time ?? undefined;
  }
}
