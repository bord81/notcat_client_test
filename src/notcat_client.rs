use notcat_lib::NotCatClient;

fn main() -> std::io::Result<()> {
    let mut client = NotCatClient::connect("/dev/socket/notcat_socket")?;
    client.log(b"Hello Rust!\n")?;
    client.close()?;
    Ok(())
}