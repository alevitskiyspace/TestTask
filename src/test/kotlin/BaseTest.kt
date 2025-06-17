package com.space307.qa

import com.space307.qa.web.utils.common.Constants.PLATFORM_PATH
import com.space307.qa.web.utils.junit.extension.environment.EnvironmentExtension
import com.space307.qa.web.utils.junit.extension.magic_hour.MagicHourExtension
import com.space307.qa.web.utils.junit.extension.timeout_killer.TimeoutExtension
import org.junit.jupiter.api.extension.ExtendWith

@Suppress("UnnecessaryAbstractClass")
@ExtendWith(
    MagicHourExtension::class,
    EnvironmentExtension::class,
    TimeoutExtension::class,
    TestsConfiguration::class
)
abstract class BaseTest {
    open fun getPath() = PLATFORM_PATH
}
