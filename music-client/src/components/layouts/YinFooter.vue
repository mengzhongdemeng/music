<template>
  <div class='the-footer'>
    <p v-for='(item, index) in footerList' :key='index'>
      {{item}}
    </p>
  </div>
</template>

<script>
export default {
  name: 'YinFooter',
  data () {
    return {
      footerList: [
      ]
    }
  }
}
</script>

<style lang='scss' scoped>
@import '@/assets/css/yin-footer.scss';
</style>
