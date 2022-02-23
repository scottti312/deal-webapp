from bs4 import BeautifulSoup
import requests


def search(URL):
    HEADERS = ({'User-Agent':
                    'Mozilla/5.0 (X11; Linux x86_64)'
                    'AppleWebKit/537.36 (KHTML, like Gecko)'
                    'Chrome/95.0.4638.69 Safari/537.36',
                'Accept-language': 'en-US,en;q=0.5'})

    webpage = requests.get(URL, headers=HEADERS)
    soup = BeautifulSoup(webpage.content, "html.parser")

    title = soup.find("h1", {"class": "heading-5 v-fw-regular"})

    title_value = title.string
    title_string = title_value.strip().replace(',', '')
    print("Title:", title_string)

    price_code = soup.find("div", {"class": "priceView-hero-price priceView-customer-price"})
    print("Price:", price_code.span.string)


url = "https://www.bestbuy.com/site/samsung-odyssey-g7-28-ips-1ms-4k-uhd-freesync-g-sync-compatible-gaming-monitor-with-hdr-black/6463480.p?skuId=6463480"
search(url)