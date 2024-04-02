<template>
    <div>
        <div class="data-wrap">
            <label for="coin">마켓 </label>
            <select name="market" id="market-select" v-model="data.market">
                <option value="upbit">업비트</option>
                <option value="bithumb">빗썸</option>
            </select>
        </div>
        <div>
            <button @click="getCoinList">모든 코인 조회</button>
        </div>
        <div v-if="data.coinList.length > 0" class="coin-list-outer">
            <div class="coin-list-wrap" v-for="(coin, idx) in data.coinList" :key="idx">
                {{ coin }}
            </div>
        </div>
    </div>
</template>

<script setup>
import { reactive } from "vue";
import axios from "axios";

const data = reactive({
    market: 'upbit',
    coinList: [],

});

const getCoinList = () => {
    axios({
        method: 'get',
        url: '/coins?market=' + data.market
    }).then((res) => {
        console.log(res);
        if (res.data.success) {
            data.coinList = res.data.data;
        } else {
            data.coinList = [];
            alert(res.data.message);
        }        
    }).catch((error) => {
        console.log(error);
    });
};
</script>

<style scoped>
.coin-list-outer {
    margin: 0 auto;
    width: 150px;
    height: 400px;
    overflow: scroll;
    overflow-x: hidden;
}
.coin-list-wrap {
    margin: 5px;
}
</style>