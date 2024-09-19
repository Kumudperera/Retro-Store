use serde::Serialize;

#[derive(Debug)]
#[derive(Serialize)]
pub struct ProcessExecutionPeriod {
    pub id: i32,
    pub name: String,
    pub description: String,
}