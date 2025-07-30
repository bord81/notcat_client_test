use notcat_lib::LogPriority;
use notcat_lib::*;
use rand::Rng;
use std::{thread, time::Duration};

fn main() -> std::io::Result<()> {
    log_init(notcat_lib::ANDROID_LOGCAT_SINK | notcat_lib::LOCAL_FILE_SINK)?;
    log(LogPriority::Info, b"Main", b"Hello Rust!\n");
    for i in 0..10 {
        thread::spawn(move || {
            for j in 0..100 {
                let delay = rand::thread_rng().gen_range(5..100);
                thread::sleep(Duration::from_millis(delay));
                log(
                    LogPriority::Debug,
                    b"Main",
                    format!(
                        "Message number from Rust thread {} (with delay) {}\n",
                        i,
                        (i * 10) + j
                    )
                    .as_bytes(),
                );
            }
            for k in 101..200 {
                log(
                    LogPriority::Debug,
                    b"Main",
                    format!(
                        "Message number from Rust thread {} (no delay) {}\n",
                        i,
                        (i * 10) + k
                    )
                    .as_bytes(),
                );
            }
        });
    }
    log(LogPriority::Info, b"Main", b"Rust client finished test\n");
    thread::sleep(Duration::from_secs(u32::MAX.into()));
    close()?;
    Ok(())
}
