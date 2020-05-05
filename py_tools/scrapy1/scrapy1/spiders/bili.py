# -*- coding: utf-8 -*-
import scrapy
import time

from scrapy import signals
import scrapy
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import time


class BiliSpider(scrapy.Spider):
    name = 'bili'
    allowed_domains = ['bilibili.com']
    start_urls = ['https://space.bilibili.com/11299971/']

    custom_settings = {
        'LOG_LEVEL': 'DEBUG',
        'LOG_FILE': 'log/%s_log_%s.txt' % (name, time.time()),  # 配置的日志
        "DEFAULT_REQUEST_HEADERS": {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36',
        }
    }

    def start_requests(self):
        for url in self.start_urls:
            yield scrapy.Request(url, self.parse)

    def parse(self, response):
        self.log(response)
        with open('bilibili.reponse', 'wb') as f:
            f.write(response.body)
