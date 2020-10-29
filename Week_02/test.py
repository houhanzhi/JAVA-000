"""
Python 2.7 GC脚本测试
"""
import os
import re


if __name__ == "__main__":

    command = ["java -cp /Users/houhanzhi/java进阶/jvmDemo/src/main/java/", "xmx", "xms", "gc", "gcDetails/GCLogAnalysis"]

    for memory in ["128m", "512m", "1g", "2g", "4g"]:
        for gc in ["-XX:+UseSerialGC", "-XX:+UseParallelGC", "-XX:+UseConcMarkSweepGC", "-XX:+UseG1GC"]:
            xms = "-Xms" + memory
            xmx = "-Xmx" + memory
            command[1] = xmx
            command[2] = xms
            command[3] = gc

            spend = 0
            for _ in range(0, 10):
                try:
                    cmd =" ".join(command)
                    cmd_p = os.popen(cmd)
                    cmd_res = cmd_p.read()
                    cmd_p.close()
                    cmd_num = re.sub("\D","",cmd_res)
                    spend = spend+int(cmd_num)
                    print(memory, gc, "------------->生成对象次数",cmd_num)
                except Exception as e:
                    print(memory, gc, "----->发生OOM")
            print(memory, gc, "平均值----->", spend / 10)
