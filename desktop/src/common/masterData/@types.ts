export class ProcessExecutionPeriod {
  id: number;
  name: string;
  description: string;
  constructor(data?: any) {
    this.id = data?.id || null;
    this.name = data?.name || "";
    this.description = data?.description || "";
  }
}
