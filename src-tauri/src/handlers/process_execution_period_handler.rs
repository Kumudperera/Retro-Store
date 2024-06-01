
use crate::dao::process_execution_period_dao::ProcessExecutionPeriodDao;
use crate::models::process_execution_period::ProcessExecutionPeriod;

#[tauri::command]
fn get_all() -> Result<Vec<ProcessExecutionPeriod>, String> {

    match ProcessExecutionPeriodDao::new() {
        Ok(dao) => dao.get_all(),
        Err(e) => {
            eprintln!("Failed to execute query: {}", e);
            Err("Failed to execute query".to_string())
        }
    }
}

pub fn process_execution_period_handler() {
    tauri::Builder::default()
        .invoke_handler(tauri::generate_handler![get_all])
        .run(tauri::generate_context!())
        .expect("error while running tauri application");
}