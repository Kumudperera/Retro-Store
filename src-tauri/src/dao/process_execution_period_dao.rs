use mysql::prelude::Queryable;

use crate::database::db::DB;
use crate::models::process_execution_period::ProcessExecutionPeriod;

pub struct ProcessExecutionPeriodDao {
    pub db: DB,
}

impl ProcessExecutionPeriodDao {
    pub fn new() -> Result<Self, String> {
        match DB::new() {
            Ok(db) => Ok(Self { db }),
            Err(e) => {
                eprintln!("Failed to connect to the database: {}", e);
                Err("Failed to connect to the database".to_string())
            }
        }
    }

    pub fn get_all(mut self) -> Result<Vec<ProcessExecutionPeriod>, String> {
        match self.db.conn.query_map(
            "SELECT * FROM process_execution_period",
            |(id, name, description)| ProcessExecutionPeriod {
                id,
                name,
                description,
            },
        ) {
            Ok(all) => {
                Ok(all)
            }
            Err(e) => {
                eprintln!("Failed to execute query: {}", e);
                Err("Failed to execute query".to_string())
            }
        }
    }
}
