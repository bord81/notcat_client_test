#include "notcat_client.h"
#include <chrono>
#include <iostream>
#include <random>
#include <string>
#include <thread>

using namespace std::chrono_literals;

int main() {
    auto* client = notcat_connect("/dev/socket/notcat_socket");
    if (!client) {
        std::cerr << "connect failed\n";
        return 1;
    }
    if (notcat_log(client, "Hello C++!\n") != 0) {
        std::cerr << "send failed\n";
    }
    std::mt19937_64 eng{std::random_device{}()};
    for (int i = 0; i < 1000; ++i) {
        std::uniform_int_distribution<int> dist(5, 100);
        std::string message = "Message number from C++ " + std::to_string(i) + "\n";
        if (notcat_log(client, message.c_str()) != 0) {
            std::cerr << "send failed\n";
        }
        std::this_thread::sleep_for(dist(eng) * 1ms);
    }
    if (notcat_log(client, "C++ finished test\n") != 0) {
        std::cerr << "send failed\n";
    }
    std::this_thread::sleep_for(876000h);
    notcat_close(client);
    return 0;
}
