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

    title = soup.find("h1")

    title_value = title.string
    title_string = title_value.strip().replace(',', '')
    print(title_string)

    price_code = soup.find("span", {"itemprop": "price"})
    price = price_code.string
    print(price)

    img = soup.find("img", attrs={"loading": "lazy"})
    print(img.get("src"))

url = sys.argv[1]
# url = "https://www.walmart.com/ip/Sceptre-40-Class-FHD-1080P-LED-TV-X405BV-FSR/683540269"
search(url)
