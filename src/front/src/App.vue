<template>
  <h1>coin-hub</h1>
  <coinPriceInfo></coinPriceInfo>
  <marketCoinList></marketCoinList>
</template>

<script setup>
import coinPriceInfo from "./components/CoinPriceInfo.vue";
import marketCoinList from "./components/MarketCoinList.vue";


</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.data-wrap {
    margin: 5px 0px;
}
</style>
