const SWIPE_ARTICLE_ID_LIST = [
    'recently-youtube-swipe',
    'best-youtube-swipe',
    'recently-item-swipe',
    'best-item-swipe',
];

const activeSwipeArticle = (articeClassName) => {
    new Swiper(`.${articeClassName}`, {
        allowTouchMove: false,
      navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev"
      }
    });
}

const productItemList = [
   {
    itemThumbnail: 'img/foam6.jpg',
    itemName: '바디맵 폼롤러',
    itemPrice: 12000,
    itemGrade: 4.8,
    reviewCount: 6570,
   },
   {
    itemThumbnail: 'img/foam7.jpg',
    itemName: '아고진 폼롤러',
    itemPrice: 12000,
    itemGrade: 4.8,
    reviewCount: 8016,
   },
   {
    itemThumbnail: 'img/band3.jpg',
    itemName: '이고진 벤드',
    itemPrice: 15000,
    itemGrade: 4.7,
    reviewCount: 8605,
   },
   {
    itemThumbnail: 'img/band4.jpg',
    itemName: '테라 밴드',
    itemPrice: 9900,
    itemGrade: 4.7,
    reviewCount: 3866,
   }
   
]

const renderProductList = () => {
    let productItemHtml =  '<div class="swiper-slide item-list">';
    productItemHtml += productItemList.map((productITem) => `
    <span class='recently-item'>
        <div class="r-item"><a href=""><img src="${productITem.itemThumbnail}"  alt="${productITem.itemName}"></a></div>
        <div>${productITem.itemName}</div>
        <div>${productITem.itemPrice}원</div>
        <div>평점<span>${productITem.itemGrade}</span>·리뷰<span>${productITem.reviewCount}</span></div>
    </span>
    `).join('');
    productItemHtml += '</div>'

    document.querySelector('.recently-item-swipe .swiper-wrapper').innerHTML += productItemHtml;

}


const main = () => {
    renderProductList();

    console.log()
    SWIPE_ARTICLE_ID_LIST.forEach((id) => activeSwipeArticle(id))
};

main();
