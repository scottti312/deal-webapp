from bs4 import BeautifulSoup
import requests
import sys

def search(URL):
    HEADERS = ({'User-Agent':
                    'Mozilla/5.0 (X11; Linux x86_64)'
                    'AppleWebKit/537.36 (KHTML, like Gecko)'
                    'Chrome/95.0.4638.69 Safari/537.36',
                'Accept-language': 'en-US,en;q=0.5'})

    webpage = requests.get(URL, headers=HEADERS)
    soup = BeautifulSoup(webpage.content, "lxml")

    title = soup.find("h1", {"class": "heading-5 v-fw-regular"})

    title_value = title.string
    title_string = title_value.strip().replace(',', '')
    print(title_string)

    price_code = soup.find("div", {"class": "priceView-hero-price priceView-customer-price"})
    print(price_code.span.string)

    img = soup.find("img", attrs={"class": "primary-image"})
    print(img.get("src"))

url = sys.argv[1]
# url = "https://www.bestbuy.com/site/samsung-odyssey-g7-28-ips-1ms-4k-uhd-freesync-g-sync-compatible-gaming-monitor-with-hdr-black/6463480.p?skuId=6463480"
search(url)
