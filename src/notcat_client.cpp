#include "notcat_client.h"
#include <chrono>
#include <iostream>
#include <random>
#include <string>
#include <thread>

using namespace std::chrono_literals;

int main() {
    auto conn_res = notcat_init(SINK_TYPE_LOCAL_FILE | SINK_TYPE_ANDROID_LOGCAT);
    if (conn_res) {
        std::cerr << "connect failed\n";
        return 1;
    }
    notcat_log(LOG_INFO, "Main", "Hello C++!\n");
    std::mt19937_64 eng{std::random_device{}()};
    for (int i = 0; i < 1000; ++i) {
        std::uniform_int_distribution<int> dist(5, 100);
        std::string message = "Message number from C++ " + std::to_string(i) + "\n";
        notcat_log(LOG_DEBUG, "Main", message.c_str());
        std::this_thread::sleep_for(dist(eng) * 1ms);
    }
    notcat_log(LOG_INFO, "Main", "C++ finished test\n");
    std::this_thread::sleep_for(876000h);
    notcat_close();
    return 0;
}
