<template>
    <div style="margin-top:50px">
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
            alert(res.data.message);
        }        
    }).catch((error) => {
        console.log(error);
    });
};
</script>

<style scoped>

</style>