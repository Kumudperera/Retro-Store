use mysql::*;

pub struct DB {
    pub conn: PooledConn,
}

impl DB {
    pub fn new() -> Result<Self> {
        let pool = Pool::new("mysql://root:root@localhost:3306/retro_store")?;
        let conn = pool.get_conn()?;
        
        Ok(Self { conn })
    }
}
