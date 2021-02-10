import os

import pymysql

st = '''---
title: "{$title}"
date: {$create}
lastmod: {$update}
draft: false
keywords: [fordawn]
description: ""
tags: []
categories: [${category}]
author: "fordawn"

# You can also close(false) or open(true) something for this content.
# P.S. comment can only be closed
comment: false
toc: true
autoCollapseToc: false
postMetaInFooter: false
hiddenFromHomePage: false
# You can also define another contentCopyright. e.g. contentCopyright: "This is another copyright."
contentCopyright: false
reward: true
mathjax: false
mathjaxEnableSingleDollar: false
mathjaxEnableAutoNumber: false

# You unlisted posts you might want not want the header or footer to show
hideHeaderAndFooter: false

# You can enable or disable out-of-date content warning for individual post.
# Comment this out to use the global config.
#enableOutdatedInfoWarning: false

flowchartDiagrams:
  enable: false
  options: ""

sequenceDiagrams: 
  enable: false
  options: ""

---

'''


def conn_mysql():
    conn = pymysql.connect(host='localhost', port=3306, user='root', password='black', db='ava24', charset='utf8')
    cur = conn.cursor()
    cur.execute('select title, doc_md, c.name, a.created_at, a.updated_at from articles a '
                'join categories c on a.cid = c.id')
    result = cur.fetchall()
    cur.close()
    conn.close()
    # result.
    # print(result)
    index = 1
    for row in result:
        print(str(row[3])[:4])
        makefile(row)
        index += 1
        if index > 2:
            # break
            pass


def makefile(row):
    current_dir = os.getcwd()
    content_dir = current_dir + '/post/' + str(row[3])[:4] + '/'
    if not os.path.exists(content_dir):
        os.mkdir(content_dir)

    file_name = content_dir + row[0] + '.md'
    with open(file_name, mode='w', encoding='utf8') as fp:
        content = st.replace('{$title}', row[0]) \
            .replace('{$create}', str(row[3])) \
            .replace('{$update}', str(row[4])) \
            .replace('${category}', row[2])
        fp.write(content)
        fp.write(row[1])


if __name__ == '__main__':
    conn_mysql()
