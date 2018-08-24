fun main(args: Array<String>) {
    val orig = "Wxtk Vnlmhfxk,\n" +
            "Px tkx atiir mh pxevhfx tl hnk gxpxlm vnlmhfxk hy hnk Ixvnkx bWkhiWkbox vehnw ybex latkbgz lxkobvx. T Ityx ietvx yhk tee rhnk ybexl.\n" +
            "Lmhkx tgrybex bWkhiWkbox Imtkml rhn pbma 15 IU hy ykxx hgebgx hk hyyebgx Imhktzx, lh rhn vtg dxxi ptkxs, ubytkbxl, itbgmbgzl, yetzl, ybkfptkxl, ubmvhbgl, pkbmxnil – tgrmabgz.\n" +
            "Lxx rhnk Imnyy torpaxkx Rhnk ybex bg bWkhiWkbox vtg ux kxtvaxw ykhf tgr ynhutgbsxk, Iftkm ykbwzx 2000, Iftkm atnl, Mxfih-t-ftmbv, hk txwbt iv. Lh paxkxoxk rhn zh, thnk ybexlyheehp.\n" +
            "Latkx ybexi tgw yhewxkl Rhn vtg jnbyder bgobmx hmaxkl mh obxp, whpgehtw, tgw vheetuhktmx hg tee max ybexl rhn ptgm obt xftbe tmmtvafxgml. Cnlm zbox maxf max ebgd VMY{vtxltkvbiaxkbltinulmbmnmbhgvbiaxk} tgw maxr vtg twvxl tee rhnk wtmt.\n" +
            "Yhk xqtfiex, axkx'l t eblm hy ybexl matm rhn'kx vnkkxgmer Imhkbgz pbma nl: hyyanu_ybkfptkx.ubg ChagMK BHM_vkxwxgmbtel.iwyl (wxexmxwl) Pbgmxkfnmxw Yhhutgbsxk9000_Ftgnte.iwy Pbgmxkfnmxw yhh.bvh\n" +
            "Mnkuh\n" +
            "Lbgvx px mtdx lxvnkbmr oxkr Ixkbhnler tgw bg hkwxk mh ikhmxvm rhn tztbglm onegxktubebmbxl ebdx xytbe tgw tfwyetpl, px'kx Ixgwbgz rhn rhnk vkxwxgmbtel nlbgz max mbfx-ikhoxg fbebmtkr-zktwx vtxltk Irffxmkby vbiaxk.\n" +
            "Atiir bWkhiWkbobgz!\n"

//    val map = mutableMapOf<Char, Char>()
//    map['y'] = 'f'
//    map['h'] = 'o'
//    map['u'] = 'b'
//    map['t'] = 'a'
//    map['g'] = 'n'

//    println('a'.toInt())
//    println('z'.toInt())

    for (c in orig){
        var char: Char = c
        if (!char.isLetter()){
            print(char)
            continue
        }
        val uppercase = char.isUpperCase()
        if (uppercase) char = char.toLowerCase()
        var code = char.toInt()-19
        if (code<97) code += 26
        char = code.toChar()
        if (uppercase) char = char.toUpperCase()
        print(char)
    }
}