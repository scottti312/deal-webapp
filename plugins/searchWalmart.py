from bs4 import BeautifulSoup
import requests
import sys
import re

def search(URL):
    HEADERS = ({'User-Agent':
                    # 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0',
                    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.137 Safari/537.36',
                    'Accept-Language': 'en-US, en;q=0.5'})

    webpage = requests.get(URL, headers=HEADERS)
    soup = BeautifulSoup(webpage.content, "html.parser")

    title = soup.find("h1")

    title_value = title.string
    title_string = title_value.strip().replace(',', '')
    print(title_string)

    price_code = soup.find("span", {"itemprop": "price"})
    price = price_code.string
    print(price)

    img = soup.find("img", attrs={"loading": "lazy"})
    print(img.get("src"))

def find(query):
    HEADERS = ({'User-Agent':
                    # 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0',
                    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.137 Safari/537.36',
                    'Accept-Language': 'en-US, en;q=0.5'})

    URL = "https://www.walmart.com/search?q=" + query
    webpage = requests.get(URL, headers=HEADERS)
    soup = BeautifulSoup(webpage.content, "lxml")

    links = []
    number = 0
    for link in soup.find_all('a', attrs={'href': re.compile("/ip/")}):
        links.append(link.get('href'))
        number += 1
        
    print(links)
    resultlink = "https://www.walmart.com" + links[0]
    return resultlink

if '.com' in sys.argv[1]:
    search(sys.argv[1])
else:
    print(find(sys.argv[1]))