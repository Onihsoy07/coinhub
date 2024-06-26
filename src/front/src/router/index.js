import { createWebHistory, createRouter } from "vue-router";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            name: "mainHome",
            component: () => import("@/components/MainHome.vue")
        },
        {
            path: "/coin-price",
            name: "coinPriceInfo",
            component: () => import("@/components/CoinPriceInfo.vue")
        },
        {
            path: "/coin-list",
            name: "marketCoinList",
            component: () => import("@/components/MarketCoinList.vue")
        },
        {
            path: "/coin-common",
            name: "commonCoin",
            component: () => import("@/components/CommonCoin.vue")
        }
    ]
});

export default router;