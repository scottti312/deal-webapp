from bs4 import BeautifulSoup
import requests
import sys

def search(URL):
    HEADERS = ({'User-Agent':
                'Mozilla/5.0 (X11; Linux x86_64)'
                    'AppleWebKit/537.36 (KHTML, like Gecko)'
                            'Chrome/44.0.2403.157 Safari/537.36',
                                'Accept-Language': 'en-US, en;q=0.5'})
 
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

url = sys.argv[1]
# url = "https://www.officedepot.com/a/products/6981096/LG-27-UltraGear-Full-HD-IPS/"
search(url)
 