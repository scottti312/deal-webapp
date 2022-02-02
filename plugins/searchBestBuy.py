from bs4 import BeautifulSoup
import requests
import sys
import re

def search(URL):
    HEADERS = ({'User-Agent':
                    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.137 Safari/537.36',
                    'Accept-Language': 'en-US, en;q=0.5'})

    webpage = requests.get(URL, headers=HEADERS)
    soup = BeautifulSoup(webpage.content, "html.parser")

    title = soup.find("h1", {"class": "heading-5 v-fw-regular"})

    title_value = title.string
    title_string = title_value.strip().replace(',', '')
    print(title_string)

    price_code = soup.find("div", {"class": "priceView-hero-price priceView-customer-price"})
    print(price_code.span.string)

    img = soup.find("img", attrs={"class": "primary-image"})
    print(img.get("src"))

def find(query):
    HEADERS = ({'User-Agent':
                    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.137 Safari/537.36',
                    'Accept-Language': 'en-US, en;q=0.5'})
    URL = 'https://www.bestbuy.com/site/searchpage.jsp?st=' + query
    webpage = requests.get(URL, headers=HEADERS)
    soup = BeautifulSoup(webpage.content, "lxml")

    links = []
    # number = 0
    for link in soup.find_all('a', attrs={'href': re.compile("skuId=")}):
        # if 'amazon' in link:
        links.append(link.get('href'))
        # number += 1
    resultlink = "https://www.bestbuy.com" + links[0]
    return resultlink


if '.com' in sys.argv[1]:
    search(sys.argv[1])
else:
    print(find(sys.argv[1]))