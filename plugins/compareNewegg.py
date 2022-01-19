from bs4 import BeautifulSoup
import requests
import sys
import re
 
# This url will contain the product search (bestbuy.com/this+product+search)
def find(query):
    HEADERS = ({'User-Agent':
                    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.137 Safari/537.36',
                    'Accept-Language': 'en-US, en;q=0.5'})

    URL = "https://www.newegg.com/p/pl?d=" + query
    webpage = requests.get(URL, headers=HEADERS)
    soup = BeautifulSoup(webpage.content, "lxml")

    links = []
    number = 0
    for link in soup.find_all('a', attrs={'href': re.compile("-_-Product")}):
        # if 'amazon' in link:
        links.append(link.get('href'))
        number += 1
    resultlink = links[0]
    return resultlink
