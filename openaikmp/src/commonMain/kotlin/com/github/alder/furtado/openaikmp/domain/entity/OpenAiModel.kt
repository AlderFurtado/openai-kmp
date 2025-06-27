package com.github.alder.furtado.openaikmp.domain.entity

enum class OpenAiModel(val code: String ="gpt-4.1") {
    GPT_4_1("gpt-4.1"),
    GPT_4_1_MINI("gpt-4.1-mini"),
    GPT_4_1_NANO("gpt-4.1-nano"),
    GPT_4_5_PREVIEW("gpt-4.5-preview"),
    GPT_4O("gpt-4o"),
    GPT_4O_AUDIO("gpt-4o-audio-preview"),
    GPT_4O_REALTIME("gpt-4o-realtime-preview"),
    GPT_4O_MINI("gpt-4o-mini"),
    GPT_4O_MINI_AUDIO("gpt-4o-mini-audio-preview"),
    GPT_4O_MINI_REALTIME("gpt-4o-mini-realtime-preview"),
    GPT_4O_MINI_SEARCH("gpt-4o-mini-search-preview"),
    GPT_4O_SEARCH("gpt-4o-search-preview"),
    COMPUTER_USE("computer-use-preview"),
    O1("o1"),
    O1_PRO("o1-pro"),
    O1_MINI("o1-mini"),
    O3("o3"),
    O3_PRO("o3-pro"),
    O3_MINI("o3-mini"),
    O4_MINI("o4-mini"),
    CODEX_MINI("codex-mini-latest"),
    GPT_IMAGE_1("gpt-image-1");
}