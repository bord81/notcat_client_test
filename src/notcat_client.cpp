#include "notcat_client.h"
#include <iostream>

int main() {
    auto* client = notcat_connect("/dev/socket/notcat_socket");
    if (!client) {
        std::cerr << "connect failed\n";
        return 1;
    }
    if (notcat_log(client, "Hello C++!\n") != 0) {
        std::cerr << "send failed\n";
    }
    notcat_close(client);
    return 0;
}
