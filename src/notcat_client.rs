use notcat_lib::LogPriority;
use notcat_lib::NotCatClient;
use rand::Rng;
use std::{thread, time::Duration};

fn main() -> std::io::Result<()> {
    let mut client = NotCatClient::connect("/dev/socket/notcat_socket")?;
    client.log(LogPriority::Info, b"Hello Rust!\n")?;
    for i in 0..10 {
        for j in 0..100 {
            let delay = rand::thread_rng().gen_range(5..100);
            thread::sleep(Duration::from_millis(delay));
            client.log(
                LogPriority::Debug,
                format!("Message number from Rust (with delay) {}\n", (i * 10) + j).as_bytes(),
            )?;
        }
        for k in 101..200 {
            client.log(
                LogPriority::Debug,
                format!("Message number from Rust  (no delay) {}\n", (i * 10) + k).as_bytes(),
            )?;
        }
    }
    client.log(LogPriority::Info, b"Rust client finished test\n")?;
    thread::sleep(Duration::from_secs(u32::MAX.into()));
    client.close()?;
    Ok(())
}
