from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException
import time

print('start..')
startTime = time.time()
print(startTime)

chrome_options = Options()
chrome_options.add_argument('--headless')  # 使用无头谷歌浏览器模式
chrome_options.add_argument('--disable-gpu')
chrome_options.add_argument('--no-sandbox')
chrome_options.add_argument('blink-settings=imagesEnabled=false')
chrome_options.add_argument('user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.70 Safari/537.36')

# 指定谷歌浏览器路径
driver = webdriver.Chrome(options=chrome_options,
                          executable_path="C:\Program Files (x86)\Google\Chrome\Application\chromedriver.exe")
url = 'https://space.bilibili.com/11299971/'

driver.get(url)
time.sleep(1)
WebDriverWait(driver, 2).until(EC.presence_of_element_located((By.CLASS_NAME, 'content')))
span = driver.find_element_by_id("h-name")
fan_num = driver.find_element_by_id("n-fs")
fan_num_text = fan_num.text
if fan_num_text[-1] == "万":
    fan_num_text = float(fan_num_text[:-1]) * 10000

print(fan_num_text)

html = driver.page_source
driver.quit()

with open('bilibili.html', 'w', encoding='utf-8') as f:
    f.write(html)
