// Prevents additional console window on Windows in release, DO NOT REMOVE!!
#![cfg_attr(not(debug_assertions), windows_subsystem = "windows")]

mod dao;
mod database;
mod models;
mod handlers;

use serde::Deserialize;

use handlers::process_execution_period_handler::process_execution_period_handler;

#[derive(Deserialize, Debug)]
#[serde(rename_all = "UPPERCASE")]
enum ExecutionPeriod {
    DAILY,
    WEEKLY,
    MONTHLY,
}
/* #[derive(Deserialize, Debug)]
#[serde(rename_all = "camelCase")]
struct ChooseFolderDTO {
    execution_time: String,
    selected_folder: String,
    execution_period: ExecutionPeriod,
} */

fn main() {
    process_execution_period_handler();
}
