from bs4 import BeautifulSoup
import requests
import sys
import re

def search(URL):
    HEADERS = ({'User-Agent':
                    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.137 Safari/537.36',
                    'Accept-Language': 'en-US, en;q=0.5'})
 
    if not URL:
        print("Product not found")
        return
    webpage = requests.get(URL, headers=HEADERS)
    soup = BeautifulSoup(webpage.content, "lxml")

    title = soup.find("h1", attrs={"class": "od-heading od-heading-h1 sku-heading", 
                      "itemprop": "name", "auid": "sku-heading", "role": "heading", "aria-level": "1" })
    title_value = title.string
    title_string = title_value.strip().replace(',', '')
    print(title_string)

    price_code = soup.find("span", attrs={"class": "od-graphql-price-big-price"})
    price = price_code.string
    print(price)

    img = soup.find("div", attrs={"class": "image-gallery-image"})
    img_tag = img.find("img")
    print(img_tag.get("src"))


def find(query):
    HEADERS = ({'User-Agent':
                    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.137 Safari/537.36',
                    'Accept-Language': 'en-US, en;q=0.5'})

    URL = "https://www.officedepot.com/catalog/search.do?Ntt=" + query
    webpage = requests.get(URL, headers=HEADERS)
    soup = BeautifulSoup(webpage.content, "lxml")

    links = []
    for link in soup.find_all('a', attrs={'href': re.compile("/a/products/")}):
        links.append(link.get('href'))
    resultlink = ""
    try:
        resultlink = "https://www.officedepot.com" + links[7]
    except IndexError:
        return resultlink
    return resultlink

if '.com' in sys.argv[1]:
    search(sys.argv[1])
else:
    print(find(sys.argv[1]))