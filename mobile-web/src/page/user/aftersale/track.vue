<template>
    <div  style="background: #f7f7f7;">
        <headerNav title="进度详情"/>
        
        <van-cell-group>
            <van-cell title="服务单号" value="18081609422771742" />
        </van-cell-group>
        <van-steps direction="vertical" :active="0" active-color="#f60" style="margin-top: 15px;">
            <van-step>
                <h3>您的服务单435703816的商品已收到</h3>
                <p>2016-07-12 12:40</p>
            </van-step>
            <van-step>
                <h3>【城市】物流状态2</h3>
                <p>2016-07-11 10:00</p>
            </van-step>
            <van-step>
                <h3>快件已发货</h3>
                <p>2016-07-10 09:30</p>
            </van-step>
        </van-steps>
        
    </div>
</template>

<script>
export default {

}
</script>

<style>

</style>
