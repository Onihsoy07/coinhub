<template>
    <div>
        <div class="data-wrap">
            <div>
                <label for="coin1">마켓1 </label>
                <select name="market1" id="market-select1" v-model="data.market1">
                    <option value="upbit">업비트</option>
                    <option value="bithumb">빗썸</option>
                </select>
            </div>
            <div>
                <label for="coin2">마켓2 </label>
                <select name="market2" id="market-select2" v-model="data.market2">
                    <option value="upbit">업비트</option>
                    <option value="bithumb">빗썸</option>
                </select>
            </div>
        </div>
        <div class="data-wrap">
            <button @click="getCommonCoin">전송</button>
        </div>
        <div v-if="data.commonCoinList.length > 0" class="coin-list-outer">
            <div class="coin-list-wrap" v-for="(coin, idx) in data.commonCoinList" :key="idx">
                {{ coin }}
            </div>
        </div>
    </div>
</template>

<script setup>
import { reactive } from "vue";
import axios from "axios";

const data = reactive({
    market1: 'upbit',
    market2: 'bithumb',
    commonCoinList: [],

});

const getCommonCoin = () => {
    if (data.market1 === data.market2) {
        data.commonCoinList = [];
        alert("같은 거래소입니다. 다른 거래소로 바꿔주세요");
        return;
    }

    axios({
        method: 'get',
        url: '/common-coins?market1=' + data.market1 + '&market2=' + data.market2
    }).then((res) => {
        console.log(res);
        if (res.data.success) {
            data.commonCoinList = res.data.data;
        } else {
            data.commonCoinList = '';
            alert(res.data.message);
        }
    }).catch((error) => {
        console.log(error);
    });
}
</script>

<style scoped>

</style>