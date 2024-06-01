use serde::Serialize;

#[derive(Serialize)]
pub struct Response<T> {
    pub status: u16,
    pub body: T,
}

impl<T> Response<T> {
    pub fn new(status: u16, body: T) -> Self {
        Self { status, body }
    }
}

