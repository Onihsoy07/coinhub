<template>
    <div>
        <div>
            <div class="data-wrap">
                <label for="coin">마켓 </label>
                <select name="market" id="market-select" v-model="data.market">
                    <option value="upbit">업비트</option>
                    <option value="bithumb">빗썸</option>
                </select>
            </div>
            <div class="data-wrap">
                <label for="coin">코인 </label>
                <input type="text" v-model="data.coin" />
            </div>
            <div>
                <button @click="getCoinPrice">확인</button>
            </div>
            <div v-if="data.currentCoinPrice" class="data-wrap">
                ￦ {{ data.currentCoinPrice }}
            </div>
        </div>
    </div>
</template>

<script setup>
import axios from "axios";
import { reactive } from "vue";

const data = reactive({
    market: 'upbit',
    coin: '',
    currentCoinPrice: '',

});

const getCoinPrice = () => {
    axios({
        method: 'get',
        url: '/price?market=' + data.market + '&coin=' + data.coin
    }).then((res) => {
        console.log(res);
        if (res.data.success) {
            data.currentCoinPrice = Number(res.data.data).toLocaleString('ko-KR');
        } else {
            alert(res.data.message);
        }        
    }).catch((error) => {
        console.log(error);
    });
};


</script>

<style scoped>
.data-wrap {
    margin: 5px 0px;
}
</style>